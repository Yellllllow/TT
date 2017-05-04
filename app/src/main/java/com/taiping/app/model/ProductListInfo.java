package com.taiping.app.model;

import java.util.List;

/**
 * Created by zhoujy on 2017/3/6.
 */

public class ProductListInfo {
    private List<ProductsBean> products;

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class ProductsBean {
        /**
         * name : 太平爱宝贝综合意外伤害保险
         * code : 3141
         * nickname : 爱宝贝
         * imgs : [{"small":"http://url"},{"big":"http://url"}]
         * title : 爱宝贝
         * labels : [{"label":"少儿保险"},{"label":"意外险"},{"label":"医疗保障"}]
         * source : 太平寿险
         * dealer :
         * price : 45元／100元
         * saleQuantity : 0
         */

        private String name;
        private String code;
        private String nickname;
        private String title;
        private String source;
        private String dealer;
        private String price;
        private String saleQuantity;
        private List<ImgsBean> imgs;
        private List<LabelsBean> labels;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getDealer() {
            return dealer;
        }

        public void setDealer(String dealer) {
            this.dealer = dealer;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSaleQuantity() {
            return saleQuantity;
        }

        public void setSaleQuantity(String saleQuantity) {
            this.saleQuantity = saleQuantity;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public List<LabelsBean> getLabels() {
            return labels;
        }

        public void setLabels(List<LabelsBean> labels) {
            this.labels = labels;
        }

        public static class ImgsBean {
            /**
             * small : http://url
             * big : http://url
             */

            private String small;
            private String big;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getBig() {
                return big;
            }

            public void setBig(String big) {
                this.big = big;
            }
        }

        public static class LabelsBean {
            /**
             * label : 少儿保险
             */

            private String label;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }
        }
    }
}
