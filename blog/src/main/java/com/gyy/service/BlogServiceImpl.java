package com.gyy.service;

import com.gyy.dao.BlogRepository;
import com.gyy.exception.NotFoundException;
import com.gyy.pojo.Blog;
import com.gyy.pojo.Type;
import com.gyy.util.MarkdownUtils;
import com.gyy.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Transactional
    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.getOne(id);
        if(blog == null){
            throw new NotFoundException("该博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        //根据id进行更新浏览次数的增加
        blogRepository.updateViews(id);
        return b;
    }

    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                ArrayList<Predicate> predicates = new ArrayList<>();
                //如果标题不为空，则加入到查询条件
                if(blog.getTitle() != null && !"".equals(blog)){
                    predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                //类型不为空加入查询条件
                if(blog.getTypeId() != null){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                //是否为推荐博文
                if(blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }

                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Page<Blog> listBlog(Long id, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //blog关联的tags，只要tags里的id和指定的相同就加入到集合中
                Join join = root.join("tags");
                return cb.equal(join.get("id"),id);
            }
        },pageable);
    }

    @Transactional
    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        PageRequest of = PageRequest.of(0, size, sort);
        return blogRepository.findTopBy(of);
    }

    @Transactional
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for(String year:years){
            map.put(year,blogRepository.findByYear(year));
        }
        return map;
    }

    @Transactional
    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    @Transactional
    @Override
    public Page<Blog> listBlog(String query,Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }

    @Override
    @Transactional
    public Blog saveBlog(Blog blog) {
        if(blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            //初始为0观看
            blog.setViews(0);
        }else{
            blog.setUpdateTime(new Date());
            //保持其他属性的不变
            blog.setCreateTime(this.getBlog(blog.getId()).getCreateTime());
        }
        return blogRepository.save(blog);
    }

    @Override
    @Transactional
    public Blog updateBlog(Long id, Blog blog) {
        Blog one = blogRepository.getOne(id);
        if(one == null){
            throw new NotFoundException("该博客不存在");
        }
        //忽略掉创建时间
        BeanUtils.copyProperties(blog,one,"creatTime");
        return blogRepository.save(one);
    }

    @Override
    @Transactional
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
