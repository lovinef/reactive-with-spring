package com.test.react.service;

import com.test.react.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserService {
    private JpaUserRepository jpaUserRepository;
}
