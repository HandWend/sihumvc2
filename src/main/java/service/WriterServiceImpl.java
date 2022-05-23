package service;

import domain.BoardVO;
import mapper.WriterMapper;

public class WriterServiceImpl implements WriterService{

	@Override
	public void insert(BoardVO vo) {
		WriterMapper mapper = new WriterMapper();
		mapper.insert(vo);
	}

	

}
