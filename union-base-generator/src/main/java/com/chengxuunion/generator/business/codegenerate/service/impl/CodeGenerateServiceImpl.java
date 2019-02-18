package com.chengxuunion.generator.business.codegenerate.service.impl;

import com.alibaba.fastjson.JSON;
import com.chengxuunion.generator.business.childtemplate.model.ChildTemplate;
import com.chengxuunion.generator.business.childtemplate.service.ChildTemplateService;
import com.chengxuunion.generator.business.codegenerate.model.request.GenerateCodeParam;
import com.chengxuunion.generator.business.context.model.Context;
import com.chengxuunion.generator.business.context.service.ContextService;
import com.chengxuunion.generator.business.engine.model.Engine;
import com.chengxuunion.generator.business.engine.service.EngineService;
import com.chengxuunion.generator.business.template.model.Template;
import com.chengxuunion.generator.business.template.service.TemplateService;
import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.generator.common.model.FilePath;
import com.chengxuunion.generator.common.util.DownloadUtil;
import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.generator.common.util.SpringUtil;
import com.chengxuunion.generator.component.context.AbstractDataContext;
import com.chengxuunion.generator.component.context.ExtContext;
import com.chengxuunion.generator.component.context.simple.DefaultDataContext;
import com.chengxuunion.generator.component.engine.EngineClient;
import com.chengxuunion.util.collection.CollectionUtils;
import com.chengxuunion.util.file.FilePathUtils;
import com.chengxuunion.util.file.FileUtils;
import com.chengxuunion.util.file.ZipUtils;
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
import java.lang.reflect.Field;
import java.util.*;

import com.chengxuunion.generator.business.codegenerate.model.CodeGenerate;
import com.chengxuunion.generator.business.codegenerate.service.CodeGenerateService;
import com.chengxuunion.generator.business.codegenerate.dao.CodeGenerateDao;
import com.chengxuunion.generator.business.codegenerate.model.request.CodeGeneratePageParam;

import javax.servlet.http.HttpServletResponse;


/**
 * @Author youpanpan
 * @Description:    代码生成记录服务实现
 * @Date:创建时间: 2019-01-10 14:49:05
 * @Modified By:
 */
@Service
public class CodeGenerateServiceImpl implements CodeGenerateService {

    private static Logger logger = LoggerFactory.getLogger(CodeGenerateServiceImpl.class);

    @Autowired
    private CodeGenerateDao codeGenerateDao;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ChildTemplateService childTemplateService;

    @Autowired
    private EngineService engineService;

    @Autowired
    private ContextService contextService;

    @Value("${code.dir}")
    private String codeDir;

    @Override
    public PageResult<CodeGenerate> listCodeGeneratePage(CodeGeneratePageParam codeGeneratePageParam) {
        PageHelper.startPage(codeGeneratePageParam.getPageNum() , codeGeneratePageParam.getPageSize());

        List<CodeGenerate> codeGenerateList = null;
        if (SessionUtils.isAdmin()) {
            codeGenerateList = codeGenerateDao.listCodeGenerate(codeGeneratePageParam);
        } else {
            codeGeneratePageParam.setUserId(SessionUtils.getUser().getId());
            codeGenerateList = codeGenerateDao.listCodeGenerateByUser(codeGeneratePageParam);
        }
        //得到分页的结果对象
        PageInfo<CodeGenerate> pageInfo = new PageInfo<>(codeGenerateList);

        return new PageResult<CodeGenerate>(codeGeneratePageParam, pageInfo.getTotal(), codeGenerateList);
    }

    /**
     * 根据主键查询单个代码生成记录对象
     *
     * @param id 主键
     * @return  单个代码生成记录对象
     */
    @Override
    public CodeGenerate getCodeGenerate(Long id) {
        return codeGenerateDao.getCodeGenerate(id);
    }

    /**
     * 保存代码生成记录对象
     *
     * @param codeGenerate 代码生成记录对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveCodeGenerate(CodeGenerate codeGenerate) {
        codeGenerate.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        codeGenerate.setCreateDate(nowDate);
        return codeGenerateDao.saveCodeGenerate(codeGenerate);
    }

    /**
     * 更新代码生成记录对象
     *
     * @param codeGenerate 代码生成记录对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateCodeGenerate(CodeGenerate codeGenerate) {
        checkAuth(codeGenerate.getId());
        return  codeGenerateDao.updateCodeGenerate(codeGenerate);
    }

    /**
     * 根据主键删除代码生成记录
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteCodeGenerate(Long id) {
        checkAuth(id);
        return  codeGenerateDao.deleteCodeGenerate(id);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Boolean generateCode(GenerateCodeParam generateCodeParam) {
        AbstractDataContext dataContext = null;
        // 实例化上下文
        if (CollectionUtils.isEmpty(generateCodeParam.getContextMap())) {
            dataContext = new DefaultDataContext();
        } else {
            try {
                AbstractDataContext lastDataContext = new DefaultDataContext();
                for (Map.Entry<String, Object> entry : generateCodeParam.getContextMap().entrySet()) {
                    Long contextId = Long.parseLong(entry.getKey());
                    Context context = contextService.getContext(contextId);
                    Class contextClazz = Class.forName(context.getClassName());
                    AbstractDataContext curDataContext = (AbstractDataContext)SpringUtil.getBean(contextClazz);
                    Class paramClazz = Class.forName(context.getParamClassName());
                    Object obj = JSON.parseObject(JSON.toJSONString(entry.getValue()), paramClazz);

                    Field[] fields = curDataContext.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        if (field.getType() == paramClazz || field.getType().isAssignableFrom(paramClazz)) {
                            field.set(curDataContext, obj);
                        }
                    }

                    curDataContext.setDataContext(lastDataContext);
                    lastDataContext = curDataContext;
                }
                dataContext = lastDataContext;
            } catch (ClassNotFoundException e ) {
                logger.error("指定的类未找到", e);
                return false;
            } catch (IllegalAccessException e) {
                logger.error("字段非法访问", e);
                return false;
            }
        }

        Template template = null;
        OutputStream out = null;
        try {
            List<String> filePathList = new ArrayList<>();
            template = templateService.getTemplate(generateCodeParam.getTemplateId());
            if (StringUtils.isEquals(Constants.TEMPLATE_TYPE_GROUP, template.getTemplateType())) {
                List<ChildTemplate> childTemplateList = childTemplateService.listChildTemplateByParentId(template.getId());
                template.setChildTemplateList(childTemplateList);
                for (ChildTemplate childTemplate : childTemplateList) {
                    FilePath codePath = generateCodeForSingleTemplate(childTemplate.getTemplate(), dataContext.getContext());
                    filePathList.add(codePath.getFileFullPath());
                }
            } else {
                FilePath codePath = generateCodeForSingleTemplate(template, dataContext.getContext());
                filePathList.add(codePath.getFileFullPath());
            }

            if (CollectionUtils.isNotEmpty(filePathList)) {
                // 压缩生成的代码
                String codeZipFileName = FilePathUtils.buildDatePathDay() + File.separator + IdGenerator.getInstance().nextIdString() + ".zip";
                String codeZipPath = codeDir + File.separator + codeZipFileName;
                FileUtils.createFile(codeZipPath);
                out = new FileOutputStream(new File(codeZipPath));
                ZipUtils.compress(filePathList, out);

                // 保存代码生成记录
                CodeGenerate codeGenerate = new CodeGenerate();
                codeGenerate.setId(IdGenerator.getInstance().nextId());
                codeGenerate.setTemplateId(generateCodeParam.getTemplateId());
                codeGenerate.setCodeFileName(generateCodeParam.getCodeFileName());
                codeGenerate.setCodePath(codeZipFileName);
                codeGenerate.setCodeSize(new File(codeZipPath).length());
                codeGenerate.setCreateDate(new Date());
                codeGenerate.setCreateUserId(SessionUtils.getUser().getId());
                codeGenerateDao.saveCodeGenerate(codeGenerate);

                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    /**
     * 根据单模版生成代码
     *
     * @param template
     * @param extContext
     * @return  代码文件目录地址
     * @throws Exception
     */
    private FilePath generateCodeForSingleTemplate(Template template, ExtContext extContext) throws Exception{
        Engine engine = engineService.getEngine(template.getEngineId());
        EngineClient engineClient = null;
        try {
            engineClient = (EngineClient)SpringUtil.getBean(Class.forName(engine.getClassName()));
        } catch (Exception e) {
            logger.error("初始化模版引擎出现异常", e);
        }
        FilePath codePath = engineClient.parseTemplate(template, extContext);
        return codePath;
    }

    @Override
    public Boolean downloadCode(Long id, HttpServletResponse response) {
        CodeGenerate codeGenerate = this.getCodeGenerate(id);
        if (codeGenerate == null || StringUtils.isEmpty(codeGenerate.getCodePath())) {
            return false;
        }

        File file = new File(codeDir + File.separator + codeGenerate.getCodePath());
        if (!file.exists()) {
            return false;
        }

        return DownloadUtil.download(response, codeDir + File.separator + codeGenerate.getCodePath(),
                codeGenerate.getCodeFileName() + ".zip");
    }

    /**
     * 判断当前用户是否有权限操作
     *
     * @param id
     */
    private void checkAuth(Long id) {
        if (!SessionUtils.isAdmin()) {
            Long createUserId = getCodeGenerate(id).getCreateUserId();
            if (!SessionUtils.getUser().getId().equals(createUserId)) {
                throw new RuntimeException("代码不是本人生成，操作失败！");
            }
        }
    }
}

