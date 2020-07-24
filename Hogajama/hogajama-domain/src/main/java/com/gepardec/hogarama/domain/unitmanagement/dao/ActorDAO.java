package com.gepardec.hogarama.domain.unitmanagement.dao;

import com.gepardec.hogarama.domain.unitmanagement.entity.Actor;
import com.gepardec.hogarama.domain.unitmanagement.entity.Owner;
import com.gepardec.hogarama.domain.unitmanagement.entity.QActor;
import com.querydsl.jpa.impl.JPAQuery;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;

@Dependent
public class ActorDAO extends BaseDAO<Actor> {

    @Override
    public Class<Actor> getEntityClass() {
        return Actor.class;
    }

    public List<Actor> getAllActorsForOwner(Owner owner) {
        JPAQuery<Actor> query = new JPAQuery<>(entityManager);
        QActor actor = QActor.actor;
        return query.select(actor).from(actor).where(actor.unit.owner.id.eq(owner.getId())).fetch();
    }

    public Optional<Actor> getByDeviceId(String id) {
        JPAQuery<Actor> query = new JPAQuery<>(entityManager);
        QActor actor = QActor.actor;
        return Optional.ofNullable(query.select(actor).from(actor).where(actor.deviceId.eq(id)).fetchOne());
    }
}
