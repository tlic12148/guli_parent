package com.tlic.guli.service.edu.service.impl;

import com.tlic.guli.service.edu.entity.Comment;
import com.tlic.guli.service.edu.mapper.CommentMapper;
import com.tlic.guli.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author T-lic
 * @since 2022-09-10
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
