import React from 'react';
import ScrollableTabView from 'react-native-top-tab-view';

import RecommendPost from './recommendPost';
import FriendPost from './friendPost';
import {TouchableOpacity} from 'react-native';
import LinearGradient from 'react-native-linear-gradient';
import IconFont from '@src/component/IconFont';
import {pxToDp} from '@src/util/pxToDp';
import SecondTabBar from '@src/component/SecondTabBar';

export default () => {
  return (
    <>
      <ScrollableTabView initialPage={0} renderTabBar={() => <SecondTabBar />}>
        <RecommendPost tabLabel="推荐" />
        <FriendPost tabLabel="关注" />
      </ScrollableTabView>
      <TouchableOpacity
        style={{position: 'absolute', right: '10%', bottom: '8%'}}
        // onPress={() => this.context.navigate('Publish')}
      >
        <LinearGradient
          colors={['#39DBD5', '#37DC8A']}
          start={{x: 0, y: 0}}
          end={{x: 1, y: 1}}
          style={{
            width: pxToDp(64),
            height: pxToDp(64),
            borderRadius: pxToDp(32),
            alignItems: 'center',
            justifyContent: 'center',
          }}>
          <IconFont
            style={{color: '#fff', fontSize: pxToDp(64)}}
            name="iconAdd"
          />
        </LinearGradient>
      </TouchableOpacity>
    </>
  );
};
