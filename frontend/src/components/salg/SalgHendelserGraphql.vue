<template>

  <div>

    <div class="w3-container" id="menu">
      <div class="w3-content" style="max-width:700px">

        <h5 class="w3-center w3-padding-48"><span class="w3-tag w3-wide">Salgshendelser</span></h5>
        <div class="w3-container w3-padding-48 w3-card">

        </div>
        <div class="w3-container w3-padding-48 w3-card">
          <table class="w3-hoverable w3-striped " width="80%">
            <tr class="w3-cell-top" align="left">
              <th>Salg</th>
              <th>Hendelse</th>
              <th>Oppdatert</th>
            </tr>
            <tr v-for="hendelse in salgsHendelser" class="w3-hover-opacity" v-model="salgsHendelser">
              <td class="w3-text-grey"><h5>{{hendelse.salgId}}</h5></td>
              <td class="w3-text-grey">{{hendelse.hendelse}}</td>
              <td class="w3-text-grey"><h5>{{hendelse.opprettet}}</h5></td>
            </tr>
          </table>
        </div>

        <!--img src="/w3images/coffeehouse2.jpg" style="width:100%;max-width:1000px;margin-top:32px;"-->
      </div>
    </div>
  </div>

</template>

<script>

  import gpl from 'graphql-tag'


  let GRAPHQL_HENT_SALGSHENDELSER = gpl`
  query SalgsHendelser($offset: Int, $antall: Int){
    salgsHendelser(offset: $offset, antall: $antall) {
      id
      salgId
      opprettet
      hendelse
    }
  }`;


  export default {

    data() {
      return {
        salgsHendelser: [],
        offset: 0,
        antall: 5
      }
    },
    methods: {
      hentSalgsHendelser(nyttOffset) {
        const offset = nyttOffset
        const antall = this.antall

        this.$apollo.query({
            query: GRAPHQL_HENT_SALGSHENDELSER,
            variables: { offset, antall }
          }
        ).then(response => {
          (this.salgsHendelser = response.data.salg)
        })
      }
    },
    mounted() {
      console.log('Henter alt på nytt')
      this.$apollo.queries.salgsHendelser.refetch()
    },
    apollo: {
      salgsHendelser: {
        query: GRAPHQL_HENT_SALGSHENDELSER,
        variables: { offset:0, antall:5 }
      }
    }
  }


</script>