package pl.javastart.equipy.user.dto;

import pl.javastart.equipy.user.User;

public class UserMapper {
    public static UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setFirstName(user.getFirstName());
                userDto.setLastName(user.getLastName());
                userDto.setPesel(user.getPesel());
        return userDto;
    }
}
