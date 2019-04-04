<template>
  <div>
    <input v-model="produktTilLagring.beskrivelse" placeholder="Beskrivelse">
    <input v-model="produktTilLagring.pris" placeholder="Pris">
    <button v-if="produktTilLagring.id == ''" v-on:click.stop.prevent="opprettProdukt">Opprett produkt</button>
    <button v-if="produktTilLagring.id != ''" v-on:click.stop.prevent="lagreProdukt">Lagre produkt</button>

    <p>{{ melding }}</p>
    <li v-for="produkt in produkter" >
      <button v-on:click="slettProdukt(produkt.id)">Slett</button>
      <router-link :to="{name:'produkt_lagre', params:{ id: produkt.id }}">{{produkt.beskrivelse}} - pris: {{produkt.pris}}</router-link>
    </li>

  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        melding: "",
        knappTekst: 'Opprett produkt',
        produkter: [],
        produktTilLagring: {
          id: '',
          beskrivelse: '',
          pris:''
        }
      }
    },
    methods: {
      tømFelter(){
        this.produktTilLagring.id = ''
        this.produktTilLagring.beskrivelse= '',
          this.produktTilLagring.pris = ''
      },
      lagreProdukt() {
        axios
          .put('/api/produkt/' + this.$route.params.id,
              this.produktTilLagring
            , {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Produkt er oppdatert!'
            this.hentAlle()
            this.tømFelter()
            this.$router.push('/produkt')
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      opprettProdukt() {

        axios
          .put('/api/produkt/', this.produktTilLagring,
            {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Produkt er oppdatert!'
            this.hentAlle()
            this.tømFelter()
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      slettProdukt(id) {
        axios.delete('/api/produkt/' + id, {},
          {}
        ).then(response => {
          this.hentAlle()
        })
      },
      hentAlle() {
        axios.get('/api/produkt', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.produkter = response.data))
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });

      },
    },

    mounted() {
      console.log('hent alle')
      this.hentAlle()
    },
    watch: {
      '$route.params.id'(newId, oldId) {
        this.melding = ''
        if (this.$route.params.id == undefined) {
          'Opprett kunde'
          return
        }

        axios.get('/api/produkt/' + this.$route.params.id, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.produktTilLagring = response.data))

      }
    }
  }
</script>