<template>
  <div id="list-selgere">

    <p>Liste med selgere {{ selgere }}</p>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    data () {
      return {
        selgere : ""
      }
    },
    mounted() {
      axios.get('http://localhost:8080/ansatt/1', {},
        {
          headers: {
            'Content-type': 'application/json',
          }
        }
      )
        .then(response => (this.melding = response))

      this.selgere = 'Hentet selgere'

      console.log(this.selgere )

    },
    methods: {
      opprett () {
        return ""
      }
    }
  }
</script>