package com.group6.ads.services.advertises;

import com.group6.ads.controllers.admin.advertises.models.AdvertiseEditByRootRequest;
import com.group6.ads.controllers.admin.advertises.models.AdvertiseEditRequest;
import com.group6.ads.controllers.admin.advertises.models.AdvertiseLicensingRequest;
import com.group6.ads.controllers.admin.advertises.models.AdvertiseRequest;
import com.group6.ads.controllers.admin.advertises.models.AdvertiseStatusRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.advertise.types.AdvertiseType;
import com.group6.ads.repositories.database.advertise.types.AdvertiseTypeRepository;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.AdvertiseRepository;
import com.group6.ads.repositories.database.advertises.edit.AdvertiseEdit;
import com.group6.ads.repositories.database.advertises.edit.AdvertiseEditRepository;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.LocationRepository;
import com.group6.ads.repositories.database.users.User;
import com.group6.ads.repositories.database.users.UserRepository;
import com.group6.ads.util.PageRequestCustom;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * com.group6.ads.services.advertise
 * Create by Dang Ngoc Tien
 * Date 12/18/2023 - 11:27 PM
 * Description: ...
 */
@Service
@RequiredArgsConstructor
public class AdvertiseServiceImpl implements AdvertiseService {

    @NonNull
    final AdvertiseRepository advertiseRepository;
    @NonNull
    final AdvertiseTypeRepository advertiseTypeRepository;
    @NonNull
    final LocationRepository locationRepository;
    @NonNull
    UserRepository userRepository;
    @NonNull
    AdvertiseEditRepository advertiseEditRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Page<Advertise> findAll(String search, PageRequestCustom pageRequestCustom) {
        try {
            logger.info("Find all advertise");
            return advertiseRepository.findAll(search, pageRequestCustom.pageRequest());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise");
        }
    }

    @Override
    public Page<Advertise> findAllUnLicensingAdvertisements(Integer propertyId, Integer parentId, String search, PageRequestCustom pageRequestCustom) {
           try {
                logger.info("Find all advertise");
                return advertiseRepository.findAllUnauthorizedAdvertisements(propertyId, parentId, search, pageRequestCustom.pageRequest());
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new NotFoundException("Not found advertise");
            }
    }

    @Override
    public Advertise findById(Integer id) {
        try {
            logger.info("Find advertise by id");
            return advertiseRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise");
        }
    }

    @Override
    public Page<Advertise> findAllByLocationId(Integer locationId, String search,
                                               PageRequestCustom pageRequestCustom) {
        try {
            logger.info("Find all advertise by location id");
            return advertiseRepository.findAllByLocationId(locationId, search, pageRequestCustom.pageRequest());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise");
        }
    }

    @Override
    public Advertise create(Integer locationId, AdvertiseRequest advertiseRequest) {
        try {
            logger.info("Create new advertise");
            Location location = locationRepository
                    .findById(locationId)
                    .orElseThrow();
            AdvertiseType advertiseType = advertiseTypeRepository
                    .findById(advertiseRequest.getAdsTypeId())
                    .orElseThrow();

            Advertise newAdvertise = Advertise.builder()
                    .location(location)
                    .licensing(advertiseRequest.getLicensing())
                    .height(advertiseRequest.getHeight())
                    .width(advertiseRequest.getWidth())
                    .adsType(advertiseType)
                    .pillarQuantity(advertiseRequest.getPillarQuantity())
                    .images(advertiseRequest.getImages())
                    .statusEdit(false)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            return advertiseRepository.save(newAdvertise);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise");
        }
    }

    @Override
    public Advertise updateByDCMS(Integer advertiseId, AdvertiseEditByRootRequest advertiseEditByRootRequest) {
        try {
            logger.info("Update advertise by DCMS");
            AdvertiseType advertiseType = advertiseTypeRepository
                    .findById(advertiseEditByRootRequest.getAdsTypeId())
                    .orElseThrow(() -> new NotFoundException("Advertise type not found"));
            Advertise advertise = advertiseRepository
                    .findById(advertiseId)
                    .orElseThrow(() -> new NotFoundException("Advertise not found"));

            advertise.setLicensing(advertiseEditByRootRequest.getLicensing());
            advertise.setHeight(advertiseEditByRootRequest.getHeight());
            advertise.setWidth(advertiseEditByRootRequest.getWidth());
            advertise.setAdsType(advertiseType);
            advertise.setImages(advertiseEditByRootRequest.getImages());
            advertise.setStatusEdit(false);
            advertise.setAdvertiseEdit(null);
            advertise.setUpdatedAt(LocalDateTime.now());

            return advertiseRepository.save(advertise);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise");
        }
    }

    @Override
    public Advertise updateLicense(Integer advertiseId, AdvertiseLicensingRequest advertiseRequest) {
        try {
            logger.info("Update license advertise");
            Advertise advertise = advertiseRepository
                    .findById(advertiseId)
                    .orElseThrow(() -> new NotFoundException("Advertise not found"));
            advertise.setLicensing(advertiseRequest.getLicensing());
            advertise.setUpdatedAt(LocalDateTime.now());
            return advertiseRepository.save(advertise);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise");
        }
    }

    @Override
    public Advertise updateStatus(Integer advertiseId, AdvertiseStatusRequest advertiseStatusRequest) {
        try {
            logger.info("Update status advertise");
            Advertise advertise = advertiseRepository
                    .findById(advertiseId)
                    .orElseThrow(() -> new NotFoundException("Advertise not found"));
            if (advertiseStatusRequest.getAdvertiseEditId() == null) {
                advertise.setAdvertiseEdit(null);
            } else {
                AdvertiseEdit advertiseEdit = advertiseEditRepository
                        .findById(advertiseStatusRequest.getAdvertiseEditId())
                        .orElseThrow(() -> new NotFoundException("Advertise edit not found"));
                advertise.setAdvertiseEdit(advertiseEdit);
            }
            advertise.setStatusEdit(advertiseStatusRequest.getStatusEdit());

            advertise.setUpdatedAt(LocalDateTime.now());
            return advertiseRepository.save(advertise);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise");
        }
    }

    @Override
    public void deleteAdvertiseEdit(Integer advertiseEditId) {
        try {
            logger.info("Delete advertise edit");
            AdvertiseEdit advertiseEdit = advertiseEditRepository
                    .findById(advertiseEditId)
                    .orElseThrow(() -> new NotFoundException("Advertise edit not found"));
            advertiseEditRepository.delete(advertiseEdit);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise edit");
        }
    }

    @Override
    public void delete(Integer advertiseId) {
        try {
            logger.info("Delete advertise");
            Advertise advertise = advertiseRepository
                    .findById(advertiseId)
                    .orElseThrow(() -> new NotFoundException("Advertise not found"));
            advertiseRepository.delete(advertise);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise");
        }
    }

    @Transactional
    @Override
    public AdvertiseEdit update(Integer advertiseId, AdvertiseEditRequest advertiseEditRequest) {
        try {
            logger.info("Update advertise");
            AdvertiseType advertiseType = advertiseTypeRepository
                    .findById(advertiseEditRequest.getAdsTypeId())
                    .orElseThrow(() -> new NotFoundException("Advertise type not found"));
            Advertise advertise = advertiseRepository
                    .findById(advertiseId)
                    .orElseThrow(() -> new NotFoundException("Advertise not found"));
            User user = userRepository
                    .findById(advertiseEditRequest.getUserId())
                    .orElseThrow(() -> new NotFoundException("User not found"));
            Location location = locationRepository
                    .findById(advertiseEditRequest.getLocationId())
                    .orElseThrow(() -> new NotFoundException("Location not found"));

            AdvertiseEdit advertiseEdit = AdvertiseEdit
                    .builder()
                    .adsType(advertiseType)
                    .content(advertiseEditRequest.getContent())
                    .user(user)
                    .createdAt(LocalDateTime.now())
                    .height(advertiseEditRequest.getHeight())
                    .width(advertiseEditRequest.getWidth())
                    .images(advertiseEditRequest.getImageUrls())
                    .licensing(advertiseEditRequest.getLicensing())
                    .location(location)
                    .build();

            advertiseEditRepository.save(advertiseEdit);

            advertise.setStatusEdit(false);
            advertise.setAdvertiseEdit(advertiseEdit);
            advertise.setUpdatedAt(LocalDateTime.now());

            advertiseRepository.save(advertise);

            return advertiseEdit;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise");
        }
    }
}
