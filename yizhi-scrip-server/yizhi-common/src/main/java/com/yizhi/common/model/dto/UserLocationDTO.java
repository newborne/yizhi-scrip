package com.yizhi.common.model.dto;

import com.yizhi.common.model.pojo.mongodb.UserLocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLocationDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Long userId; //用户id
    private Double longitude; //经度
    private Double latitude; //维度
    private String address; //位置描述
    private String created; //创建时间
    private Long updated; //更新时间
    private Long lastUpdated; //上次更新时间
    public static final UserLocationDTO format(UserLocation userLocation) {
        UserLocationDTO userLocationDTO = new UserLocationDTO();
        userLocationDTO.setAddress(userLocation.getAddress());
        userLocationDTO.setCreated(String.valueOf(userLocation.getCreated()));
        userLocationDTO.setId(userLocation.getId().toHexString());
        userLocationDTO.setLastUpdated(userLocation.getLastUpdated());
        userLocationDTO.setUpdated(userLocation.getUpdated());
        userLocationDTO.setUserId(userLocation.getUserId());
        userLocationDTO.setLongitude(userLocation.getLocation().getX());
        userLocationDTO.setLatitude(userLocation.getLocation().getY());
        return userLocationDTO;
    }
    public static final List<UserLocationDTO> formatToList(List<UserLocation> userLocations) {
        List<UserLocationDTO> list = new ArrayList<>();
        for (UserLocation userLocation : userLocations) {
            list.add(format(userLocation));
        }
        return list;
    }
}
