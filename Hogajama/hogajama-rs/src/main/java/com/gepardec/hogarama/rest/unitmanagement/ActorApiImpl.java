package com.gepardec.hogarama.rest.unitmanagement;

import com.gepardec.hogarama.domain.unitmanagement.entity.Actor;
import com.gepardec.hogarama.domain.unitmanagement.service.ActorService;
import com.gepardec.hogarama.rest.unitmanagement.dto.ActorDto;
import com.gepardec.hogarama.rest.unitmanagement.interceptor.DetermineOwner;
import com.gepardec.hogarama.rest.unitmanagement.translator.ActorDtoTranslator;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@SuppressWarnings("unused")
@DetermineOwner
public class ActorApiImpl implements ActorApi {

    private static final Logger LOG = LoggerFactory.getLogger(ActorApiImpl.class);

    @Inject
    private ActorService service;
    @Inject
    private ActorDtoTranslator translator;

    @Override
    public Response getForOwner(SecurityContext securityContext) {
        LOG.info("Get actors for current owner.");
        List<ActorDto> dtoList = translator.toDtoList(service.getAllActorsForOwner());
        return new BaseResponse<>(dtoList, HttpStatus.SC_OK).createRestResponse();
    }

    @Override
    @Transactional
    public Response create(SecurityContext securityContext, ActorDto actorDto) {
        LOG.info("Create actor.");
        Actor actor = translator.fromDto(actorDto);
        service.createActor(actor);

        return new BaseResponse<>(HttpStatus.SC_CREATED).createRestResponse();
    }

    @Override
    @Transactional
    public Response update(String id, SecurityContext securityContext, ActorDto actorDto) {
        LOG.info("Updating actor with id {}.", id);
        Actor actor = translator.fromDto(actorDto);
        if (id == null || !id.equals(actorDto.getId().toString()) || actorDto.getUnitId() == null) {
            return new BaseResponse<>(HttpStatus.SC_BAD_REQUEST).createRestResponse();
        } else {
            service.updateActor(actor);
        }

        return new BaseResponse<>(HttpStatus.SC_OK).createRestResponse();
    }

    @Override
    @Transactional
    public Response delete(String id, SecurityContext securityContext) {
        LOG.info("Deleting actor with id {}.", id);
        if (id == null) {
            return new BaseResponse<>(HttpStatus.SC_BAD_REQUEST).createRestResponse();
        } else {
            Long idNum;
            try {
                idNum = Long.parseLong(id);
            } catch (NumberFormatException e) {
                return new BaseResponse<>(HttpStatus.SC_BAD_REQUEST).createRestResponse();
            }

            service.deleteActor(idNum);
        }

        return new BaseResponse<>(HttpStatus.SC_OK).createRestResponse();
    }
}
