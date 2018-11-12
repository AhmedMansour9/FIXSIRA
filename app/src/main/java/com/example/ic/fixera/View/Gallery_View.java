package com.example.ic.fixera.View;

import com.example.ic.fixera.Model.GalleryImage;
import com.example.ic.fixera.Model.Gallery_Images;

import java.util.List;

/**
 * Created by Ahmed on 10/10/2018.
 */

public interface Gallery_View {
   void listimages(List<GalleryImage> images);
   void Error();
}
