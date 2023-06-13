package com.life.web.service;

import com.life.web.repository.ProfileRepository;
import com.life.web.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * packageName    : com.life.web.service
 * fileName       : ProfileService
 * author         : kodg
 * date           : 2023/06/13
 * description    : 프로필 서비스(비지니스)
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        kodg       최초 생성
 */
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public List<BoardVo> getProfile(String userId) {

        return profileRepository.getProfile(userId);
    }
}
