package com.gyy.service;

import com.gyy.dao.TypeRepository;
import com.gyy.exception.NotFoundException;
import com.gyy.pojo.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    @Transactional
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    @Transactional
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Override
    @Transactional
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Transactional
    @Override
    public List<Type> listTypeTop(Integer size) {
        //按Type的个数倒数排序
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable of = PageRequest.of(0, size, sort);
        return typeRepository.findTopBy(of);
    }

    @Override
    @Transactional
    public Type updateType(Long id, Type type) {
        Type one = typeRepository.getOne(id);
        if(one == null){
            throw new NotFoundException("不存在");
        }
        //拷贝一份存入
        BeanUtils.copyProperties(type,one);
        return typeRepository.save(one);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }
}
