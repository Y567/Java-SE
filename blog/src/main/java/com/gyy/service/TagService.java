package com.gyy.service;

import com.gyy.pojo.Tag;
import com.gyy.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.HTML;
import java.util.List;

/**
 * 标签的操作接口类
 */
public interface TagService {
    //保存一个标签
    Tag saveTag(Tag tag);

    //查询一个标签
    Tag getTag(Long id);

    //列表分页
    Page<Tag> listTag(Pageable pageable);
    //可以根据一个字符串id获取所有的标签
    List<Tag> listTag(String tagIds);
    //查询巅峰的几个分类
    List<Tag> listTagTop(Integer size);
    List<Tag> listTag();

    //更新标签
    Tag updateTag(Long id,Tag Tag);

    //删除一个标签
    void deleteTag(Long id);

    //根据名字查询一个标签
    Tag getTagByName(String name);
}
