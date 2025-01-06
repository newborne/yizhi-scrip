// export const BASE_URL = 'http://192.168.3.3:7000';
export const BASE_URL = 'http://127.0.0.1:17000';
// login
export const LOGIN_SEND = 'easyz-login/api/v1/login/send';
export const LOGIN_LOGIN = 'easyz-login/api/v1/login/login';
export const LOGIN_SAVE_USER_INFO = 'easyz-login/api/v1/login/saveUserInfo';
export const LOGIN_SAVE_USER_LOGO = 'easyz-login/api/v1/login/saveUserLogo';
export const LOGIN_MOBILE_SEND = 'easyz-login/api/v1/login/mobile/send';
export const LOGIN_MOBILE_NEW = 'easyz-login/api/v1/login/mobile/new';
// users
export const USERS_UPDATE_LOCATION = 'easyz-server/api/v1/users/updateLocation';
export const USERS_NEAR = 'easyz-server/api/v1/users/near';
export const USERS_TODAYBEST = 'easyz-server/api/v1/users/todayBest';
export const USERS_ID = 'easyz-server/api/v1/users/:userId';
export const USERS_RECOMMEND_USER_LIST =
  'easyz-server/api/v1/users/recommendUserList';
export const USERS_SAVE_USER_LOGO = 'easyz-server/api/v1/users/saveUserLogo';
export const USERS_HUANXIN = 'easyz-server/api/v1/users/huanxin';
export const USERS_LIST = 'easyz-server/api/v1/users/list';
export const USERS_ADD_FRIENDID = 'easyz-server/api/v1/users/add/:friendId';
export const USERS_FOLLOW_FRIENDID =
  'easyz-server/api/v1/users/follow/:friendId';
export const USERS_UNFOLLOW_FRIENDID =
  'easyz-server/api/v1/users/unfollow/:friendId';
export const USERS_QUERY_USER_INFO =
  'easyz-server/api/v1/users/queryUserInfo/:userId';
export const USERS_FOLLOW_COUNTS = 'easyz-server/api/v1/users/follow/counts';
export const USERS_FOLLOW_LIST_TYPE =
  'easyz-server/api/v1/users/follow/list/:type';
export const USERS_UPDATE_USER_INFO =
  'easyz-server/api/v1/users/updateUserInfo';
// post
export const POST_RECOMMEND = 'easyz-server/api/v1/post/recommend';
export const POST_PUBLISH = 'easyz-server/api/v1/post/publish';
export const POST_USER = 'easyz-server/api/v1/post/user/:userId';
export const POST_FRIEND = 'easyz-server/api/v1/post/friend';
export const POST_ID = 'easyz-server/api/v1/post/:id';
export const POST_ID_TEXT = 'easyz-server/api/v1/comment/post/:id/text';
export const POST_ID_LIKE = 'easyz-server/api/v1/comment/post/:id/like';
export const POST_ID_DISLIKE = 'easyz-server/api/v1/comment/post/:id/dislike';
export const POST_ID_LOVE = 'easyz-server/api/v1/comment/post/:id/love';
export const POST_ID_UNLOVE = 'easyz-server/api/v1/comment/post/:id/unlove';
// video
export const VIDEO_RECOMMEND = 'easyz-server/api/v1/video/recommend';
export const VIDEO_PUBLISH = 'easyz-server/api/v1/video/publish';
export const VIDEO_USER = 'easyz-server/api/v1/video/user/:userId';
export const VIDEO_FRIEND = 'easyz-server/api/v1/video/friend';
export const VIDEO_ID = 'easyz-server/api/v1/video/:id';
export const VIDEO_ID_TEXT = 'easyz-server/api/v1/comment/video/:id/text';
export const VIDEO_ID_LIKE = 'easyz-server/api/v1/comment/video/:id/like';
export const VIDEO_ID_DISLIKE = 'easyz-server/api/v1/comment/video/:id/dislike';
export const VIDEO_ID_LOVE = 'easyz-server/api/v1/comment/video/:id/love';
export const VIDEO_ID_UNLOVE = 'easyz-server/api/v1/comment/video/:id/unlove';
// comment
export const COMMENT_PUBLISH_ID = 'easyz-server/api/v1/comment/:publishId';
export const COMMENT_ID_LIKE = 'easyz-server/api/v1/comment/comment/:id/like';
export const COMMENT_ID_DISLIKE =
  'easyz-server/api/v1/comment/comment/:id/dislike';
// message
export const MESSAGE_COMMENT_TYPE = 'easyz-server/api/v1/message/:commentType';
export const MESSAGE_ANNOUNCEMENT = 'easyz-server/api/v1/message/announcement';
// article
export const ARTICLE_SAVE = 'easyz-server/api/v1/article/save';
export const ARTICLE_TYPE = 'easyz-server/api/v1/article/list/:articleType';
export const ARTICLE_RECOMMEND_V1 = 'easyz-server/api/v1/article/recommend';
export const ARTICLE_RECOMMEND_V2 = 'easyz-server/api/v2/article/recommend';
export const ARTICLE_RID = 'easyz-server/api/v1/article/:articleRid';
export const ARTICLE_ID_LIKE = 'easyz-server/api/v1/comment/article/:id/like';
export const ARTICLE_ID_DISLIKE =
  'easyz-server/api/v1/comment/article/:id/dislike';
// material
export const MATERIAL_SAVE = 'easyz-server/api/v1/material/save';
export const MATERIAL_TYPE = 'easyz-server/api/v1/material/list/:materialType';
export const MATERIAL_RECOMMEND_V1 = 'easyz-server/api/v1/material/recommend';
export const MATERIAL_RECOMMEND_V2 = 'easyz-server/api/v2/material/recommend';
export const MATERIAL_RID = 'easyz-server/api/v1/material/:materialRid';
export const MATERIAL_ID_LOVE = 'easyz-server/api/v1/comment/material/:id/love';
export const MATERIAL_ID_UNLOVE =
  'easyz-server/api/v1/comment/material/:id/unlove';
