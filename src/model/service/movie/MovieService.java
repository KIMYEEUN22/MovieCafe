package model.service.movie;

import domain.movie.MovieInfoVo;
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
		try {
			int exists = movieInfoDao.compareMovie(movie.getMovieTitle());
			
			if(exists == 1) {
				System.out.println("�̹� ����");
			} else {
				movieInfoDao.insertMovie(movie);	
			}
		} catch (Exception e) {
			throw e;
		}
		
	}
}