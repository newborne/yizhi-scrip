import React, {Component} from 'react';
import ScrollableTabView from 'react-native-top-tab-view';
import Fan from './fan';
import Follow from './follow';
import MutualFollow from './mutualFollow';
import Visitor from './visitor';
import MultiHead from '@src/component/MultiHead';

class Index extends Component {
  render() {
    const index = this.props.route.params || 0;
    return (
      <ScrollableTabView initialPage={index} renderTabBar={() => <MultiHead />}>
        <MutualFollow tabLabel="互相关注" />
        <Follow tabLabel="关注" />
        <Fan tabLabel="粉丝" />
        <Visitor tabLabel="访客" />
      </ScrollableTabView>
    );
  }
}

export default Index;
