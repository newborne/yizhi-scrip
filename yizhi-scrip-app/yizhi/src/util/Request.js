import axios from 'axios';
import {BASE_URL} from '@src/util/Api';
import Toast from '@src/util/Toast';
import RootStore from '@src/store';

const instance = axios.create({
  baseURL: BASE_URL,
});
// 请求拦截器
instance.interceptors.request.use(
  function (config) {
    Toast.showLoading('请求中');
    return config;
  },
  function (error) {
    return Promise.reject(error);
  },
);
// 响应拦截器
instance.interceptors.response.use(
  function (response) {
    Toast.hideLoading();
    return response.data;
  },
  function (error) {
    Toast.hideLoading();
    return error.response.data;
  },
);
export default {
  get: instance.get,
  post: instance.post,
  privateGet: (url, data = {}, options = {}) => {
    const token = RootStore.token;
    const headers = options.headers || {};
    return instance.get(url, {
      ...options,
      params: data,
      headers: {
        Authorization: `${token}`,
        ...headers,
      },
    });
  },
  privatePost: (url, data = {}, options = {}) => {
    const token = RootStore.token;
    const headers = options.headers || {};
    return instance.post(url, data, {
      ...options,
      headers: {
        Authorization: `${token}`,
        ...headers,
      },
    });
  },
};
