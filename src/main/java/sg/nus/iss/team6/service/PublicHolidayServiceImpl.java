package sg.nus.iss.team6.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.team6.model.PublicHoliday;
import sg.nus.iss.team6.repository.PublicHolidayRepository;

@Service
public class PublicHolidayServiceImpl implements PublicHolidayService {
  
  @Resource
  private PublicHolidayRepository publicHolidayRepository;
  

  @Override
  public List<PublicHoliday> findAllHoliday() {
    return publicHolidayRepository.findAll();
  }

  @Override
  public List<PublicHoliday> findAllPublicHolidays() {
    return publicHolidayRepository.findAll();
  }

  @Override
  @Transactional
  public PublicHoliday findPublicHoliday(Integer id) {
    return publicHolidayRepository.findPublicHolidayById(id);
  }


  @Override
  @Transactional
  public PublicHoliday findPublicHolidayByName(String name) {
    return publicHolidayRepository.findHolidayByName(name);
  }
  
  @Override
  @Transactional
  public PublicHoliday createPublicHoliday(PublicHoliday PublicHoliday) {
    return publicHolidayRepository.saveAndFlush(PublicHoliday);
  }

  @Override
  @Transactional
  public PublicHoliday changePublicHoliday(PublicHoliday publicHoliday) {
    return publicHolidayRepository.saveAndFlush(publicHoliday);
  }

  @Override
  @Transactional
  public void removePublicHoliday(PublicHoliday publicHoliday) {
    publicHolidayRepository.delete(publicHoliday);
  }

}
