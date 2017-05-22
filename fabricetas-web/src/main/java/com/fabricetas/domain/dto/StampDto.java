package com.fabricetas.domain.dto;

import java.util.Collection;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fabricetas.domain.Color;
import com.fabricetas.domain.Invoice;
import com.fabricetas.domain.PersistentFile;
import com.fabricetas.domain.Rating;
import com.fabricetas.domain.Stamp;
import com.fabricetas.domain.Text;
import com.fabricetas.domain.Theme;
import com.fabricetas.domain.Tshirt;
import com.fabricetas.domain.User;

/**
 * Entity that models a stamp for view layer
 * Created on 20/04/2017.
 * @author belman
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode(exclude={"stampId"})
public class StampDto {

    @Getter @Setter
    private Integer stampId;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String path;

    @Getter @Setter
    private String price;

    @Getter @Setter
    private Collection<Color> color;

    @Getter @Setter
    private Collection<Invoice> invoice;

    @Getter @Setter
    private Collection<PersistentFile> persistentFile;

    @Getter @Setter
    private Collection<Rating> rating;

    @Getter @Setter
    private Collection<Text> text;

    @Getter @Setter
    private Theme theme;

    @Getter @Setter
    private Collection<Tshirt> tshirt;

    @Getter @Setter
    private User user;
    
    /*For Create*/
    public Stamp entity(){
      Stamp stampForCreate = new Stamp();
      stampForCreate.setStampId(stampId);
      stampForCreate.setDescription(description);
      stampForCreate.setName(name);
      stampForCreate.setPath(path);
      stampForCreate.setPrice(price);
      stampForCreate.setColor(color);
      stampForCreate.setInvoice(invoice);
      stampForCreate.setPersistentFile(persistentFile);
      stampForCreate.setRating(rating);
      stampForCreate.setText(text);
      stampForCreate.setTheme(theme);
      stampForCreate.setTshirt(tshirt);
      stampForCreate.setUser(user);
      return stampForCreate;
    }
    
    /*For Update*/
    public Stamp entity(Stamp stamp){
    	Stamp stampUpdated = new Stamp();
    	stampUpdated.setStampId(stampId);
    	stampUpdated.setDescription(description == null ? stamp.getDescription() : description);
    	stampUpdated.setName(name == null ? stamp.getName() : name);
    	stampUpdated.setPath(path == null ? stamp.getPath() : path);
    	stampUpdated.setPrice(price == null ? stamp.getPrice() : price);
    	stampUpdated.setColor(color == null ? stamp.getColor() : color);
    	stampUpdated.setInvoice(invoice == null ? stamp.getInvoice() : invoice);
    	stampUpdated.setPersistentFile(persistentFile == null ? stamp.getPersistentFile() : persistentFile);
    	stampUpdated.setRating(rating == null ? stamp.getRating() : rating);
    	stampUpdated.setText(text == null ? stamp.getText() : text);
    	stampUpdated.setTheme(theme == null ? stamp.getTheme() : theme);
    	stampUpdated.setTshirt(tshirt == null ? stamp.getTshirt() : tshirt);
    	stampUpdated.setUser(user == null ? stamp.getUser() : user);
    	return stampUpdated;
    }

    public StampDto(String description, String name, String path, String price, Integer stampId) {
        this.description = description; this.name = name;
        this.path = path; this.price = price; this.stampId = stampId;
    }
}
