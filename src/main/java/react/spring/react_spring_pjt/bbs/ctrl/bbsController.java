package react.spring.react_spring_pjt.bbs.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import react.spring.react_spring_pjt.bbs.domain.BbsRequestDTO;
import react.spring.react_spring_pjt.bbs.domain.BbsResponseDTO;
import react.spring.react_spring_pjt.bbs.domain.comment.CommentRequestDTO;
import react.spring.react_spring_pjt.bbs.domain.comment.CommentResponseDTO;
import react.spring.react_spring_pjt.bbs.service.BbsService;

@RestController
@RequestMapping("/bbs")
public class bbsController {

    @Autowired
    private BbsService bbsService;

    @GetMapping("/index")
    public ResponseEntity<Object> landing() {
        System.out.println("client endpoint : /bbs/index" + bbsService);
        List<BbsResponseDTO> list = bbsService.findAll();
        System.out.println("result size = " + list.size());
        if (list.size() == 0) {
            Map<String, String> map = new HashMap<>();
            map.put("info", "저장된 데이터가 존재하지 않습니다.");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody BbsRequestDTO params) {
        System.out.println("client endpoint : /bbs/save");
        System.out.println("params = " + params);
        bbsService.create(params);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<BbsResponseDTO> view(@PathVariable("id") Integer id) {
        System.out.println("client endpoint : /bbs/view" + bbsService);
        System.out.println("params = " + id);
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);

        BbsResponseDTO result = bbsService.find(map);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/view/{id}/comments")
    public ResponseEntity<Object> commentView(@PathVariable("id") Integer id) {
        System.out.println("client endpoint : /bbs/view/" + id);
        System.out.println("params = " + id);

        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);

        // 게시물 정보를 포함한 DTO를 받아옴
        BbsResponseDTO bbsResponse = bbsService.find(map);
        // 댓글 리스트만 추출
        List<CommentResponseDTO> comments = bbsResponse.getComments();

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/comment/save")
    public ResponseEntity<Void> commentSave(@RequestBody CommentRequestDTO params) {
        System.out.println("client endpoint : /bbs/comment/save");
        System.out.println("params = " + params);
        bbsService.saveComment(params);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/comment/getComment/{id}")
    public ResponseEntity<List<CommentResponseDTO>> getComment(@PathVariable(name = "id") Integer bbsId) {
        System.out.println("client endpoint : /bbs/comment/getComment/{id}");
        System.out.println("params = " + bbsId);

        Map<String, Integer> map = new HashMap<>();
        map.put("id", bbsId);

        List<CommentResponseDTO> list = bbsService.findComment(map);
        System.out.println("result size = " + list.size());

        return new ResponseEntity<List<CommentResponseDTO>>(list, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        System.out.println("client endpoint : /bbs/delete/{id}");
        System.out.println("params = " + id);

        Map<String, Integer> map = new HashMap();
        map.put("id", id);

        bbsService.delete(map);

        return new ResponseEntity<String>(id + "번째 게시물 삭제", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody BbsRequestDTO params) {
        System.out.println("client endpoint : /bbs/update/{id}");
        System.out.println("params = " + params);
        params.setId(id);
        bbsService.update(params);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
