package com.life.web.service;

import com.life.web.common.Pagination;
import com.life.web.common.PagingResponse;
import com.life.web.dto.SearchDto;
import com.life.web.repository.BoardRepository;
import com.life.web.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * packageName    : com.life.web.service
 * fileName       : BoardService
 * author         : kodg
 * date           : 2023/06/07
 * description    : 게시판 비즈니스 로직
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        kodg       최초 생성
 */

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 파일이 저장될 경로를 지정합니다.
    // Mac이나 Windows 환경에서 모두 동작하는 코드입니다.
    // 프로젝트의 루트 디렉토리에 'uploads'라는 디렉토리를 만들어 그곳에 파일을 저장합니다.
    private final Path root = Paths.get("uploads");

    public PagingResponse<BoardVo> boardList(SearchDto searchDto) throws Exception{
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = boardRepository.count(searchDto);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, searchDto);
        searchDto.setPagination(pagination);

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<BoardVo> list = boardRepository.boardList(searchDto);
        return new PagingResponse<>(list, pagination);
    }


    public void createBoard(BoardVo boardVo, MultipartFile image) throws Exception {

        //루트에 디렉토리가 없을시 생성.
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }
        try {
            // 사용자가 업로드한 파일을 파일 시스템에 저장합니다.
            // file.getInputStream()은 업로드한 파일의 내용을 읽어오는 스트림을 반환합니다.
            // this.root.resolve(file.getOriginalFilename())은 저장될 파일의 전체 경로를 반환합니다.
            Path tarGetFile = this.root.resolve(image.getOriginalFilename());
            while (Files.exists(tarGetFile)) {
                //중복파일인 경우에 랜덤숫자 추가
                String newFileName = image.getOriginalFilename()+UUID.randomUUID();
                tarGetFile = this.root.resolve(newFileName);
            }
            Files.copy(image.getInputStream(), tarGetFile);
            // 저장된 파일의 경로를 문자열로 반환합니다.
            String filePath = this.root.resolve(image.getOriginalFilename()).toString();
//            System.out.println("filePath : "+filePath);
            int startIndex = filePath.indexOf("/")+2;
//            boardVo.(filePath.substring(startIndex));
            boardRepository.createBoard(boardVo);
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
}
