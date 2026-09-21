package vn.iotstar.service;

import vn.iotstar.dto.UserDTO;

public interface UserService {

    UserDTO findByEmail(String email);
}
