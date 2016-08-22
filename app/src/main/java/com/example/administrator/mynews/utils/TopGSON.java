package com.example.administrator.mynews.utils;

import java.util.List;

/**
 * Created by Administrator on 2016/8/8.
 */
public class TopGSON {


    /**
     * postid : PHOT22RE7000100A
     * hasCover : false
     * hasHead : 1
     * replyCount : 18913
     * hasImg : 1
     * digest :
     * hasIcon : false
     * docid : 9IG74V5H00963VRO_BTUU3EPAbjlongfangupdateDoc
     * title : 揭人民币制作过程:如何从"白纸"到钞票
     * order : 1
     * priority : 340
     * lmodify : 2016-08-08 13:31:22
     * boardid : photoview_bbs
     * ads : [{"title":"甘肃丹霞景区人山人海 日游客数超3万","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/c0a40722e3974cffa1fd2ffb5d07d82620160808112401.jpeg","subtitle":"","url":"00AP0001|2190787"},{"title":"河北承德木兰围场草原上演千人瑜伽秀","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/06d7c123e6e3413c808ec3c35a224b9920160808102036.jpeg","subtitle":"","url":"00AP0001|2190775"},{"title":"韩国和朝鲜女子体操选手合影","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/3633c837da0245e8868123325eec556720160808103755.jpeg","subtitle":"","url":"00AO0001|2190779"},{"title":"泰国全民公投通过新宪法草案","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/9c93b15f1c014289b5bcd022802c099620160808095736.jpeg","subtitle":"","url":"00AO0001|2190773"},{"title":"天津街头青蛙幼崽成群结队出现引围观","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/cd23fc000a384ee7a38f0f71e22fbc1220160808112044.jpeg","subtitle":"","url":"00AP0001|2190786"}]
     * photosetID : 00AP0001|2190791
     * template : normal1
     * votecount : 17567
     * skipID : 00AP0001|2190791
     * alias : Top News
     * skipType : photoset
     * cid : C1348646712614
     * hasAD : 1
     * imgextra : [{"imgsrc":"http://cms-bucket.nosdn.127.net/2a7494a660944d95818549a6193b0fb920160808132500.jpeg"},{"imgsrc":"http://cms-bucket.nosdn.127.net/a267dd0b38ab4be0989dc8a1142c347520160808132521.jpeg"}]
     * source : 网易原创
     * ename : androidnews
     * imgsrc : http://cms-bucket.nosdn.127.net/f77d447b113c4c3e8e6c80fcbe33359a20160808132442.jpeg
     * tname : 头条
     * ptime : 2016-08-08 13:23:58
     */

    private List<T1348647909107Bean> T1348647909107;

    public List<T1348647909107Bean> getT1348647909107() {
        return T1348647909107;
    }

    public void setT1348647909107(List<T1348647909107Bean> T1348647909107) {
        this.T1348647909107 = T1348647909107;
    }

    public static class T1348647909107Bean {
        private String postid;
        private boolean hasCover;
        private int hasHead;
        private int replyCount;
        private int hasImg;
        private String digest;
        private boolean hasIcon;
        private String docid;
        private String title;
        private int order;
        private int priority;
        private String lmodify;
        private String boardid;
        private String photosetID;
        private String template;
        private int votecount;
        private String skipID;
        private String alias;
        private String skipType;
        private String cid;
        private int hasAD;
        private String source;
        private String ename;
        private String imgsrc;
        private String tname;
        private String ptime;
        /**
         * title : 甘肃丹霞景区人山人海 日游客数超3万
         * tag : photoset
         * imgsrc : http://cms-bucket.nosdn.127.net/c0a40722e3974cffa1fd2ffb5d07d82620160808112401.jpeg
         * subtitle :
         * url : 00AP0001|2190787
         */

        private List<AdsBean> ads;
        /**
         * imgsrc : http://cms-bucket.nosdn.127.net/2a7494a660944d95818549a6193b0fb920160808132500.jpeg
         */

        private List<ImgextraBean> imgextra;

        public String getPostid() {
            return postid;
        }

        public void setPostid(String postid) {
            this.postid = postid;
        }

        public boolean isHasCover() {
            return hasCover;
        }

        public void setHasCover(boolean hasCover) {
            this.hasCover = hasCover;
        }

        public int getHasHead() {
            return hasHead;
        }

        public void setHasHead(int hasHead) {
            this.hasHead = hasHead;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public int getHasImg() {
            return hasImg;
        }

        public void setHasImg(int hasImg) {
            this.hasImg = hasImg;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public boolean isHasIcon() {
            return hasIcon;
        }

        public void setHasIcon(boolean hasIcon) {
            this.hasIcon = hasIcon;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getLmodify() {
            return lmodify;
        }

        public void setLmodify(String lmodify) {
            this.lmodify = lmodify;
        }

        public String getBoardid() {
            return boardid;
        }

        public void setBoardid(String boardid) {
            this.boardid = boardid;
        }

        public String getPhotosetID() {
            return photosetID;
        }

        public void setPhotosetID(String photosetID) {
            this.photosetID = photosetID;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public int getVotecount() {
            return votecount;
        }

        public void setVotecount(int votecount) {
            this.votecount = votecount;
        }

        public String getSkipID() {
            return skipID;
        }

        public void setSkipID(String skipID) {
            this.skipID = skipID;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getSkipType() {
            return skipType;
        }

        public void setSkipType(String skipType) {
            this.skipType = skipType;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public int getHasAD() {
            return hasAD;
        }

        public void setHasAD(int hasAD) {
            this.hasAD = hasAD;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public List<AdsBean> getAds() {
            return ads;
        }

        public void setAds(List<AdsBean> ads) {
            this.ads = ads;
        }

        public List<ImgextraBean> getImgextra() {
            return imgextra;
        }

        public void setImgextra(List<ImgextraBean> imgextra) {
            this.imgextra = imgextra;
        }

        public static class AdsBean {
            private String title;
            private String tag;
            private String imgsrc;
            private String subtitle;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ImgextraBean {
            private String imgsrc;

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }
        }
    }
}
