package com.anproject.architecture_promotion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "settings")
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "meta_tag")
    private String metaTag;

    @Column(name = "small_logo")
    private String smallLogo;

    @Column(name = "large_logo")
    private String largeLogo;


}
