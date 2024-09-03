package react.spring.react_spring_pjt.bbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import react.spring.react_spring_pjt.bbs.dao.BbsMapper;
import react.spring.react_spring_pjt.bbs.domain.BbsRequestDTO;
import react.spring.react_spring_pjt.bbs.domain.BbsResponseDTO;
import react.spring.react_spring_pjt.bbs.domain.comment.CommentResponseDTO;
import react.spring.react_spring_pjt.bbs.domain.comment.CommentRequestDTO;

@Service
public class BbsService {

    @Autowired
    private BbsMapper bbsMapper;

    public List<BbsResponseDTO> findAll() {
        System.out.println("debug >>> service findAll " + bbsMapper);
        return bbsMapper.findAllRow();
    }

    public void create(BbsRequestDTO params) {
        System.out.println("debug >>> service create " + bbsMapper);
        bbsMapper.insertRow(params);
    }

    public BbsResponseDTO find(Map<String, Integer> map) {
        System.out.println("debug >>> service find " + bbsMapper);

        BbsResponseDTO result = bbsMapper.findRow(map);
        List<CommentResponseDTO> list = bbsMapper.findByIdComment(map);
        result.setComments(list);
        return result;
    }

    public void saveComment(CommentRequestDTO params) {
        System.out.println("debug >>> service saveComment " + bbsMapper);
        bbsMapper.insertCommentRow(params);
    }

    public List<CommentResponseDTO> findComment(Map<String, Integer> map) {
        System.out.println("debug >>> service findComment " + bbsMapper);
        List<CommentResponseDTO> list = bbsMapper.findByIdComment(map);
        return list;
    }

    public void delete(Map<String, Integer> map) {
        System.out.println("debug >>> service delete " + bbsMapper);
        bbsMapper.deleteRow(map);
    }

    public void update(BbsRequestDTO params) {
        System.out.println("debug >>> service update " + bbsMapper);
        bbsMapper.updateRow(params);
    }

}
