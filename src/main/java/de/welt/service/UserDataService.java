package de.welt.service;

import de.welt.domain.MergedResponse;

public interface UserDataService {

    MergedResponse requestForUser(String userId);

}
