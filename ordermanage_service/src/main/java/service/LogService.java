package service;

import dao.LogDao;
import domain.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogDao logDao;
    public void add(Log log){
        logDao.add(log);
    }
    public List<Log> all(){
        return logDao.all();
    }
}
