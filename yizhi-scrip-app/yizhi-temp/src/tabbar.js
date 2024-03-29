import React, {Component} from 'react';
import {View} from 'react-native';
import TabNavigator from 'react-native-tab-navigator';
import IconFont from '@src/component/IconFont';
import Material from '@src/page/1_material';
import Article from '@src/page/2_article';
import Publish from '@src/page/3_publish';
import Users from '@src/page/4_users';
import Info from '@src/page/5_info';

class Index extends Component {
  state = {
    selectedTab: 'material',
    pages: [
      // 首页
      {
        selected: 'material',
        title: '首页',
        renderIcon: () => <IconFont name="iconMaterial" />,
        renderSelectedIcon: () => (
          <IconFont name="home" size={32} color="#f00" />
        ),
        onPress: () => this.setState({selectedTab: 'material'}),
        component: <Material />,
      },
      // 作文
      {
        selected: 'article',
        title: '作文',
        renderIcon: () => <IconFont name="article" size={32} color="#333" />,
        renderSelectedIcon: () => (
          <IconFont name="article" size={32} color="#f00" />
        ),
        onPress: () => this.setState({selectedTab: 'article'}),
        component: <Article />,
      },
      // 创作
      {
        selected: 'publish',
        title: '创作',
        renderIcon: () => <IconFont name="publish" size={32} color="#333" />,
        renderSelectedIcon: () => (
          <IconFont name="publish" size={32} color="#f00" />
        ),
        onPress: () => this.setState({selectedTab: 'publish'}),
        component: <Publish />,
      },
      // 用户区
      {
        selected: 'users',
        title: '发现',
        renderIcon: () => <IconFont name="users" size={32} color="#333" />,
        renderSelectedIcon: () => (
          <IconFont name="users" size={32} color="#f00" />
        ),
        onPress: () => this.setState({selectedTab: 'users'}),
        component: <Users />,
      },
      // 个人中心
      {
        selected: 'info',
        title: '个人',
        renderIcon: () => <IconFont name="info" size={32} color="#333" />,
        renderSelectedIcon: () => (
          <IconFont name="info" size={32} color="#f00" />
        ),
        onPress: () => this.setState({selectedTab: 'info'}),
        component: <Info />,
      },
    ],
  };
  render() {
    const {selectedTab, pages} = this.state;
    return (
      //细节
      <View style={{flex: 1, backgroundColor: '#fff'}}>
        <TabNavigator tabBarStyle={{height: '8%'}}>
          {pages.map((v, i) => (
            <TabNavigator.Item
              key={i}
              selected={selectedTab === v.selected}
              title={v.title}
              renderIcon={v.renderIcon}
              renderSelectedIcon={v.renderSelectedIcon}
              onPress={v.onPress}
              selectedTitleStyle={{color: '#39DBD5'}}
              tabStyle={{
                backgroundColor: '#eee',
              }}>
              {v.component}
            </TabNavigator.Item>
          ))}
        </TabNavigator>
      </View>
    );
  }
}
export default Index;
