package com.goyarce.api.repositories.mysql;

import com.goyarce.api.beans.mysql.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepo extends CrudRepository<Bill, Integer> {

}
