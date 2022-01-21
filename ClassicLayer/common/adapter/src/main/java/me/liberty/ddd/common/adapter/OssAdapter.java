package me.liberty.ddd.common.adapter;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-12-27 16:39
 */
public interface OssAdapter {
    void save(String path, String[] content);
}
