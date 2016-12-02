package com.xunyue.xunmsg.xunmsg.util;

import android.content.Context;

import com.xunyue.xunmsg.xunmsg.bean.CommonBean;
import com.xunyue.xunmsg.xunmsg.imp.CommonImp;
import com.xunyue.xunmsg.xunmsg.util.CacheUtil;

import org.json.JSONArray;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2016/12/1.
 */
public class ServerCloud<T> {

    /**
     * 查询数据方法
     *
     * @param context
     * @param objId
     * @param imp
     */
    public void quary(final Context context, final String tag, final String objId, final CommonImp imp) {
        BmobQuery<T> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(objId, new QueryListener<T>() {
            @Override
            public void done(T obj, BmobException e) {
                if (e == null) {

                    imp.onSuccess(tag, obj);
                } else {
                    imp.onFail(tag, e.getMessage());
                }
            }
        });

    }

    /**
     * \
     * 保存操作
     *
     * @param bean
     * @param context
     * @param name
     */
    public void save(CommonBean bean, final Context context, final String name, final String tag, final CommonImp imp) {
        bean.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    CacheUtil.save(context, "objID", name, objectId);
                    imp.onSuccess(tag, null);
                } else {
                    imp.onFail(tag, e.getMessage());
                }
            }

        });
    }

    /**
     * 根据某个字段查询
     */
    public void queryData(String ClassName, String[] conditions, String[] conditionValues, String[] selecteds, int count, final String tag, final CommonImp imp) {
        BmobQuery query = new BmobQuery("Person");
        query.setLimit(count);
        for (int i = 0; i < conditions.length; i++) {
            query.addWhereEqualTo(conditions[i], conditionValues[i]);
        }
        for (String selected : selecteds) {
            query.order(selected);
        }
        //v3.5.0版本提供`findObjectsByTable`方法查询自定义表名的数据
        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray ary, BmobException e) {
                if (e == null) {
                    imp.onSuccess(tag, ary.toString());
                } else {
                    imp.onFail(tag, e.getMessage());
                }
            }
        });

    }

    public void quaryMore(String[] conditions, String[] conditionValues, final String tag, final CommonImp imp) {
        BmobQuery<T> query = new BmobQuery<T>();
//查询playerName叫“比目”的数据
        for (int i = 0; i < conditions.length; i++) {
            query.addWhereEqualTo(conditions[i], conditionValues[i]);
        }
//返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(10);
//执行查询方法
        query.findObjects(new FindListener<T>() {
            @Override
            public void done(List<T> object, BmobException e) {
                if (e == null) {
                    imp.onSuccess(tag, object);
                } else {
                    imp.onFail(tag, e.getMessage());
                }
            }
        });
    }

    /**
     * 修改数据操作
     *
     * @param objId
     * @param bean
     * @param tag
     * @param imp
     */
    public void updata(String objId, final CommonBean bean, final String tag, final CommonImp imp) {
        //更新Person表里面id为6b6c11c537的数据，address内容更新为“北京朝阳”
        bean.update(objId, new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    imp.onSuccess(tag, bean.getUpdatedAt());

                } else {
                    imp.onFail(tag, e.getMessage());
                }
            }

        });
    }

    /**
     * 删除操作
     *
     * @param objId
     * @param bean
     * @param tag
     * @param imp
     */
    public void delet(String objId, final CommonBean bean, final String tag, final CommonImp imp) {
        bean.setObjectId("6b6c11c537");
        bean.delete(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    imp.onSuccess(tag, bean.getUpdatedAt());
                } else {
                    imp.onFail(tag, e.getMessage());
                }
            }

        });
    }

}

