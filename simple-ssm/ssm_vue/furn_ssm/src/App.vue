<template>
  <div>
    <!--头部导航栏-->
    <Header/>
    <!--主体部-->
    <div style="display: flex;">
      <!--侧边栏-->
      <Aside/>
      <!--使用路由机制-->
      <router-view style="flex: 1"/>
    </div>
  </div>
</template>

<style>

</style>
<script>
import Aside from "@/components/Aside";
import Header from "@/components/Header";
export default{
  name: "Layout",
  components:{
    Aside,
    Header
  }
}
</script>