package com.wework.xposed.wework.wxapi

import com.wework.xposed.common.bean.Member
import com.wework.xposed.wework.WkGlobal
import com.wework.xposed.wework.util.ConversationUtil
import de.robv.android.xposed.XposedHelpers

object ProfileApi {
    /**
     * 获取当前用户的资料信息
     * @return 资料信息
     */
    fun getCurrentLoginUserProfile(): Member? {
        val profileCls = XposedHelpers.findClass("com.tencent.wework.foundation.logic.Profile", WkGlobal.workLoader)
        val profileInstance = XposedHelpers.callStaticMethod(profileCls, "getInstance")
        val userInfo = XposedHelpers.callMethod(profileInstance, "getUserInfo")
        if (null != userInfo) {
            //解析
            return ConversationUtil.convertUserInfoToMember(userInfo)
        }
        return null
    }
}