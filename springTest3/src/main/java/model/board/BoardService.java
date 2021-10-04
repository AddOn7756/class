package model.board;

import java.util.List;

public interface BoardService {

	boolean insertBoard(BoardVO vo);
	boolean updateBoard(BoardVO vo);
	boolean deleteBoard(BoardVO vo);
	List<BoardVO> getBoardList(BoardVO vo);
	BoardVO getBoard(BoardVO vo);
	
}
