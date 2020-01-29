/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author madse
 */
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    private String type;
    private int birthYear;
    private String sound;
}
