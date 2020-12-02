package com.practice.react.service;

import com.practice.react.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserService {
    private JpaUserRepository jpaUserRepository;
}
