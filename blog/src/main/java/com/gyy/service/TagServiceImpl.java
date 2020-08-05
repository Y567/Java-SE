package com.gyy.service;

import com.gyy.dao.TagRepository;
import com.gyy.exception.NotFoundException;
import com.gyy.pojo.Blog;
import com.gyy.pojo.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    @Transactional
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Override
    @Transactional
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Tag> listTag(String tagIds) {

//        System.out.println("字符串为"+tagIds);
        List<Tag> tags = new ArrayList<>();
        if(!tagIds.isEmpty()){
            String[] split = tagIds.split(",");
            for (String s : split) {
                Tag tag = this.getTag(new Long(s));
                tags.add(tag);
            }
        }
        return tags;
    }

    @Transactional
    @Override
    public List<Tag> listTagTop(Integer size) {
        //按Tag的个数倒数排序
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable of = PageRequest.of(0, size, sort);
        return tagRepository.findTopBy(of);
    }

    @Transactional
    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    @Transactional
    public Tag updateTag(Long id, Tag tag) {
        Tag one = tagRepository.getOne(id);
        if(one == null){
            throw new NotFoundException("不存在");
        }
        //拷贝一份存入
        BeanUtils.copyProperties(tag,one);
        return tagRepository.save(one);
    }

    @Override
    @Transactional
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
