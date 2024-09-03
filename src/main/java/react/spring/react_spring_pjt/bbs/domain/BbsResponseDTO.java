package react.spring.react_spring_pjt.bbs.domain;

import lombok.Data;
import react.spring.react_spring_pjt.bbs.domain.comment.CommentResponseDTO;
import java.util.ArrayList;
import java.util.List;

@Data
public class BbsResponseDTO {

    private Integer id;
    private String title;
    private String content;

    private List<CommentResponseDTO> comments;
}
