package react.spring.react_spring_pjt.bbs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import react.spring.react_spring_pjt.bbs.domain.BbsResponseDTO;
import react.spring.react_spring_pjt.bbs.domain.BbsRequestDTO;
import react.spring.react_spring_pjt.bbs.domain.comment.CommentResponseDTO;
import react.spring.react_spring_pjt.bbs.domain.comment.CommentRequestDTO;

@Mapper
public interface BbsMapper {

    public List<BbsResponseDTO> findAllRow();

    public void insertRow(BbsRequestDTO params);

    public BbsResponseDTO findRow(Map<String, Integer> map);

    public List<CommentResponseDTO> findByIdComment(Map<String, Integer> map);

    public void insertCommentRow(CommentRequestDTO params);

    public void deleteRow(Map<String, Integer> map);

    public void updateRow(BbsRequestDTO params);

}
