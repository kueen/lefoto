/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.cache;

import com.lefoto.common.utils.RandomUtil;
import com.lefoto.model.media.LePhoto;
import com.lefoto.service.iface.media.PhotoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
public class PhotoCache {
    //1代表搞笑 | 2代表萌宠 | 3代表童真 | 4代表美女

    static List<List<LePhoto>> photoList = new ArrayList<List<LePhoto>>(5);

    static public void initPhotoList(PhotoService photoService) {
        photoList.add(photoService.getPhotosByAdmin(0));
        photoList.add(photoService.getPhotosByAdmin(1));
        photoList.add(photoService.getPhotosByAdmin(2));
        photoList.add(photoService.getPhotosByAdmin(3));
        photoList.add(photoService.getPhotosByAdmin(4));
    }

    static public List<LePhoto> getPhotos(int cateId, int lastPhotoId, int size, int type) {
        //type : 0表示按时间顺序排序 | 1表示按热度排序 | 2便是随便看看  也就是随机排序
        int count = 0;
        List<LePhoto> photos = photoList.get(cateId);
        if (photos == null) {
            return null;
        }
        List<LePhoto> result = new ArrayList<LePhoto>();
        if (type == 0) {
            boolean flag = true;
            if (lastPhotoId == 0) {
                flag = false;
            }
            for (int index = photos.size() - 1; index >= 0; index--) {
                LePhoto photo = photos.get(index);
                if (flag) {
                    if (photo.getId() == lastPhotoId) {
                        flag = false;
                        continue;
                    }
                    continue;
                }
                result.add(photo);
                count++;
                if (count >= size) {
                    return result;
                }
            }
        }
        if (type == 2) {
            int amount = photos.size();
            int[] photoIds = RandomUtil.getUnDuplicateNums(size, 0, amount);
            int pointer = 0;
            int pointerNum = photoIds[pointer];
            for (int index = 0; index < amount; index++) {
                LePhoto photo = photos.get(index);
                if (pointerNum == index) {
                    result.add(photo);
                    pointer++;
                    if (pointer >= size) {
                        return result;
                    } else {
                        pointerNum = photoIds[pointer];
                    }
                }
            }
        }
        return null;
    }
}