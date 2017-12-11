package com.school.onlineshop.part1;

import com.school.models.Artifact;

import java.util.ArrayList;
import java.util.Iterator;


public class Basket {

    ArrayList<Artifact> productList = new ArrayList<>();
    Integer sumOfProducts;


    public void addProduct(Artifact product) {
        productList.add(product);
    }

    public Iterator<Artifact> getIterator() {

        Iterator<Artifact> ProductIterator = new ProductIterator();
        return ProductIterator;
    }

    public ArrayList<Artifact> getProductList() {

        return this.productList;
    }

    public void iterateThroughList() {

        Iterator<Artifact> it = getIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public Float calculateOrderPrice() {

        Float sum = 0f;
        for (Artifact item : this.productList) {
            sum += item.getPrice();
        }
        return sum;
    }

    public void removeProduct(Artifact product) {

        productList.remove(product);
    }

    public void clearBasket(){

        productList.clear();
    }

    public Integer getSumOfBasket(){
        int sum = 0;

        for (Artifact artifact : productList)
            sum = sum + artifact.getPrice();

        return sum;
    }


    private class ProductIterator implements Iterator {

        private int index;

        public ProductIterator() {
        }

        @Override
        public boolean hasNext() {
            return index < getProductList().size();
        }


        @Override
        public Object next() {
            return getProductList().get(index++);
        }

    }
}




