package com.chengxuunion.generator.business.template.service.impl;

import com.chengxuunion.generator.business.childtemplate.model.ChildTemplate;
import com.chengxuunion.generator.business.childtemplate.service.ChildTemplateService;
import com.chengxuunion.generator.business.templatecontext.model.TemplateContext;
import com.chengxuunion.generator.business.templatecontext.service.TemplateContextService;
import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.generator.common.model.FilePath;
import com.chengxuunion.generator.common.util.PathUtils;
import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.collection.CollectionUtils;
import com.chengxuunion.util.file.FileUtils;
import com.chengxuunion.util.id.IdGenerator;
import com.chengxuunion.util.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.chengxuunion.generator.common.model.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.*;
import java.util.*;

import com.chengxuunion.generator.business.template.model.Template;
import com.chengxuunion.generator.business.template.service.TemplateService;
import com.chengxuunion.generator.business.template.dao.TemplateDao;
import com.chengxuunion.generator.business.template.model.request.TemplatePageParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * @Author youpanpan
 * @Description:    模版服务实现
 * @Date:创建时间: 2019-01-09 14:04:49
 * @Modified By:
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    private static Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);

    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private ChildTemplateService childTemplateService;

    @Autowired
    private TemplateContextService templateContextService;

    @Value("${template.dir}")
    private String templateDir;

    @Override
    public PageResult<Template> listTemplatePage(TemplatePageParam templatePageParam) {
        PageHelper.startPage(templatePageParam.getPageNum() , templatePageParam.getPageSize());

        List<Template> templateList = null;
        if (SessionUtils.isAdmin()) {
            templateList = templateDao.listTemplate(templatePageParam);
        } else {
            templatePageParam.setUserId(SessionUtils.getUser().getId());
            templateList = templateDao.listTemplateByUser(templatePageParam);
        }
        //得到分页的结果对象
        PageInfo<Template> pageInfo = new PageInfo<>(templateList);

        return new PageResult<Template>(templatePageParam, pageInfo.getTotal(), templateList);
    }

    /**
     * 根据主键查询单个模版对象
     *
     * @param id 主键
     * @return  单个模版对象
     */
    @Override
    public Template getTemplate(Long id) {
        return templateDao.getTemplate(id);
    }

    /**
     * 保存模版对象
     *
     * @param template 模版对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveTemplate(Template template) {
        template.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        template.setCreateDate(nowDate);
        template.setUpdateDate(nowDate);

        Long userId = SessionUtils.getUser().getId();
        template.setCreateUserId(userId);

        // 如果是组合模版，则保存子模版记录
        if (StringUtils.isEquals(Constants.TEMPLATE_TYPE_GROUP, template.getTemplateType())) {
            for (ChildTemplate childTemplate : template.getChildTemplateList()) {
                childTemplate.setParentTemplateId(template.getId());
            }
            childTemplateService.saveChildTemplateBatch(template.getChildTemplateList());
        }

        // 保存依赖的上下文
        for (TemplateContext templateContext : template.getTemplateContextList()) {
            templateContext.setTemplateId(template.getId());
            templateContext.setCreateUserId(userId);
        }
        templateContextService.saveTemplateContextBatch(template.getTemplateContextList());

        return templateDao.saveTemplate(template);
    }

    /**
     * 更新模版对象
     *
     * @param template 模版对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateTemplate(Template template) {
        checkAuth(template.getId());

        template.setUpdateDate(new Date());

        // 如果是组合模版，则保存子模版记录
        if (StringUtils.isEquals(Constants.TEMPLATE_TYPE_GROUP, template.getTemplateType())) {
            childTemplateService.deleteChildTemplateByParentId(template.getId());
            for (ChildTemplate childTemplate : template.getChildTemplateList()) {
                childTemplate.setParentTemplateId(template.getId());
            }
            childTemplateService.saveChildTemplateBatch(template.getChildTemplateList());
        }

        if (CollectionUtils.isNotEmpty(template.getTemplateContextList())) {
            // 保存依赖的上下文
            for (TemplateContext templateContext : template.getTemplateContextList()) {
                templateContext.setTemplateId(template.getId());
            }
            templateContextService.deleteTemplateContextByTemplateId(template.getId());
            templateContextService.saveTemplateContextBatch(template.getTemplateContextList());
        }

        return  templateDao.updateTemplate(template);
    }

    /**
     * 根据主键删除模版
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteTemplate(Long id) {
        checkAuth(id);
        childTemplateService.deleteChildTemplateByParentId(id);
        templateContextService.deleteTemplateContextByTemplateId(id);
        return  templateDao.deleteTemplate(id);
    }

    @Override
    public Map<String, Object> uploadTemplate(MultipartFile file) {
        FilePath filePath = PathUtils.getFilePathWithDate(templateDir, file.getOriginalFilename());
        try {
            FileUtils.saveFile(filePath.getFileFullPath(), file.getInputStream());
        } catch (IOException e) {
            logger.error("保存文件出现异常", e);
            return Collections.emptyMap();
        }

        Map<String, Object> fileMap = new HashMap<>();
        fileMap.put("templateFileName", file.getOriginalFilename());
        fileMap.put("templateFileSize", file.getSize());
        fileMap.put("templatePath", filePath.getFilePath());
        return fileMap;
    }

    @Override
    public Boolean downloadTemplate(Long id, HttpServletResponse response) {
        Template template = this.getTemplate(id);
        if (template == null || StringUtils.isEmpty(template.getTemplatePath())) {
            return false;
        }

        File file = new File(templateDir + "/" + template.getTemplatePath());
        if (!file.exists()) {
            return false;
        }

        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            String fileName = template.getTemplateFileName();
            long fileLength = file.length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));

            outputStream = response.getOutputStream();
            inputStream = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int len = -1;
            while((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断当前用户是否有权限操作
     *
     * @param id
     */
    private void checkAuth(Long id) {
        if (!SessionUtils.isAdmin()) {
            Long createUserId = getTemplate(id).getCreateUserId();
            if (!SessionUtils.getUser().getId().equals(createUserId)) {
                throw new RuntimeException("模版不是本人创建，操作失败！");
            }
        }
    }
}

