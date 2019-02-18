var cookieUtil = {
    /**
     * 设置cookie
     *
     * @param	name	cookie名称
     * @param	value	cookie值
     * @param	expireTime	过期时间（毫秒数）
     */
    setCookie: function(name, value, expireTime) {
        var exdate=new Date();
        exdate.setTime(exdate.getTime() + expireTime);
        document.cookie=name+ "=" +escape(value)+((expireTime==null) ? "" : ";expires="+exdate.toGMTString())
    },

    /**
     * 获取cookie中的值
     *
     * @param	name	cookie名称
     */
    getCookie: function(name) {
        if (document.cookie.length>0) {
            c_start=document.cookie.indexOf(name + "=");

            if (c_start!=-1) {
                c_start=c_start + name.length+1
                c_end=document.cookie.indexOf(";",c_start)
                if (c_end==-1) c_end=document.cookie.length
                return unescape(document.cookie.substring(c_start,c_end))
            }
        }
        return "";
    },

    /**
     * 移除指定cookie
     *
     * @param	name	要移除的cookie名称
     */
    removeCookie: function(name) {
        this.setCookie(name, "", -1);
    }
}