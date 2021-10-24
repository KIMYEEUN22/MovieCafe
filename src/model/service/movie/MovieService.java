package model.service.movie;

import java.util.ArrayList;

import domain.movie.MovieInfoVo;
import model.dao.movie.GuanramDao;
import model.dao.movie.MovieInfoDao;

public class MovieService {

	private static MovieService movieService;
	
	private MovieService() {}
	
	public static MovieService getInstace() {
		if(movieService == null) {
			movieService = new MovieService();
		}
		return movieService;
	}
	
	// ��ȭ ���� ��� 
	public void registerMovie(MovieInfoVo movie) throws Exception {
		MovieInfoDao movieInfoDao = MovieInfoDao.getInstance();
		movieInfoDao.insertMovie(movie);		
	}
	
	// ��ȭ ���� ��
	public int compaerMovie(String movieTitle) throws Exception {
		MovieInfoDao movieInfoDao = MovieInfoDao.getInstance();
		int exists = movieInfoDao.compareMovie(movieTitle);
		return exists;
	}
	
	// ��ȭ ��� ��ȸ
	public ArrayList<MovieInfoVo> retrieveMovieList(int startRow, int postSize) throws Exception {
		MovieInfoDao movieInfoDao = MovieInfoDao.getInstance();
		return movieInfoDao.selectMovieList(startRow, postSize);
	}
	
	// ��ȭ �Խñ� ���� ���Ѵ�.
	public int retrieveTotalMovieCount() throws Exception {
		return MovieInfoDao.getInstance().selectTotlaMovieCount();
	}
	
	// ��ȭ ���� ����
	public void removeMovie(int movieNo) throws Exception {
		MovieInfoDao.getInstance().deleteMovie(movieNo);
	}
	
	// ��ȭ ���� �� ��ȸ
	public MovieInfoVo retrieveMovieDetail(String userId, int movieNo) throws Exception {
		MovieInfoVo movieDetail = new MovieInfoVo();
		movieDetail = MovieInfoDao.getInstance().selectMovie(userId, movieNo);
		movieDetail.setGuanramList(GuanramDao.getInstance().selectGuanramList(movieNo));
		System.out.println(movieDetail);
		return movieDetail;	
	}
	
}