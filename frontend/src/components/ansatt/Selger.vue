<template>
  <div id="selger">
    <EndreSelger/>
    <ListSelgere/>
    <router-view class="view"></router-view>
  </div>
</template>

<script>
  import ListSelgere from './ListSelgere.vue'
  import EndreSelger from './EndreSelger.vue'
  export default {
    components : {
      ListSelgere,
      EndreSelger
    }


  }
</script>