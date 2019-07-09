package com.goyarce.api.repositories.mysql;

import com.goyarce.api.beans.mysql.Charge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChargeRepo extends CrudRepository<Charge, Integer> {
    @Query(value =
            "select charge_id, player_id, guardian_id, description, amount, date, state, bill_id " +
                    "from charge " +
                    "where guardian_id = :id " +
                        "and bill_id is null", nativeQuery = true)
    public Iterable<Charge> getChargeByGuardian_id(@Param("id") String id);

    @Query(value =
            "select charge_id, player_id, guardian_id, description, amount, date, state, bill_id " +
                    "from charge " +
                    "where bill_id = :id ", nativeQuery = true)
    public Iterable<Charge> getChargeByBill_id(@Param("id") Integer id);
}
