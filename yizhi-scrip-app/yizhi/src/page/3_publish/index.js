import React from 'react';
import ScrollableTabView from 'react-native-top-tab-view';
import TopTabBar from '@src/component/TopTabBar';
import Post from './post';
import Video from './video';

export default () => {
  return (
    <ScrollableTabView initialPage={0} renderTabBar={() => <TopTabBar />}>
      <Post tabLabel="文章" />
      <Video tabLabel="短视频" />
    </ScrollableTabView>
  );
};
