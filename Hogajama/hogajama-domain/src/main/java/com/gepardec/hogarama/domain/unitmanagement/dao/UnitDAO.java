package com.gepardec.hogarama.domain.unitmanagement.dao;

import com.gepardec.hogarama.domain.unitmanagement.entity.QUnit;
import com.gepardec.hogarama.domain.unitmanagement.entity.Sensor;
import com.gepardec.hogarama.domain.unitmanagement.entity.Unit;
import com.querydsl.jpa.impl.JPAQuery;

import javax.enterprise.context.Dependent;
import java.util.List;

@Dependent
public class UnitDAO extends BaseDAO<Unit> {

    @Override
    public Class<Unit> getEntityClass() {
        return Unit.class;
    }

    public List<Unit> getUnitsForOwner(Long ownerId) {
        JPAQuery<Sensor> query = new JPAQuery<>(entityManager);
        QUnit unit = QUnit.unit;
        return query.select(unit).from(unit).where(unit.unit.owner.id.eq(ownerId)).fetch();
    }
}
