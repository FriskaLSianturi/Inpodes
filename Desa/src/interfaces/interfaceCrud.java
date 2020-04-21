/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author frisca
 */
public interface interfaceCrud {
    public <T> void add(T t);
    public <T> void update(T t);
    public <T> void delete(T t);
  
}
