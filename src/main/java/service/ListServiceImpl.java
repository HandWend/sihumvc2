package service;

import java.util.Collection;

import domain.BoardInfo;
import domain.BoardVO;
import mapper.ListMapper;

public class ListServiceImpl implements ListService{

	//기존에 만든 것들을 지울 필요없다.
	@Override
	public Collection<BoardVO> read() {
		return new ListMapper().read();
	}

	//deprecated: 추천하지 않는다.
	@Deprecated
	public int totalRow() {
		return new ListMapper().totalRow();
	}

	public BoardInfo boardInfo() {
		BoardInfo boardInfo = new BoardInfo();
		boardInfo.setList(new ListMapper().read());
		boardInfo.setTotalRow(new ListMapper().totalRow());
		return boardInfo;
	}

	public BoardInfo boardInfo(int startPage, int pageRow) {
		BoardInfo boardInfo = new BoardInfo();
		boardInfo.setList(new ListMapper().read(startPage, pageRow));
		boardInfo.setTotalRow(new ListMapper().totalRow());
		return boardInfo;
	}

	

}
