package soju.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import soju.vo.CalendarVO;

public interface ICalendarService {
	public ArrayList<CalendarVO> calenList(int artNum);
	public int insertSchedule(CalendarVO vo);
	public ArrayList<HashMap<String, Object>> artistList(String agcId);
	public ArrayList<HashMap<String, Object>> selectSchedule(HashMap<String, Object> map);
	public int deleteSchedule(int num);
}
